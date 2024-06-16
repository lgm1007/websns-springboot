package com.lgmpjt.websnsspringboot;

import com.google.common.base.CaseFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
@ActiveProfiles(value = "test")
public class DatabaseCleanUp implements InitializingBean {
	// DB에 테스트 데이터를 초기화해주기 위한 클래스
	// h2 in-memory 모드로 테스트 실행하면 안 해줘도 될 동작
	@PersistenceContext
	private EntityManager entityManager;

	private List<String> tableNames;

	@Override
	public void afterPropertiesSet() {
		final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		tableNames = entities.stream()
				.filter(entityType -> isEntity(entityType) && hasTableAnnotation(entityType))
				.map(entityType -> {
					String tableName = entityType.getJavaType().getAnnotation(Table.class).name();
					return tableName.isBlank() ? CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, entityType.getName()) : tableName;
				})
				.toList();

		final List<String> entityNames = entities.stream()
				.filter(entityType -> isEntity(entityType) && !hasTableAnnotation(entityType))
				.map(entityType -> CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, entityType.getName()))
				.toList();

		for (String entityType : entityNames) {
			tableNames.add(entityType);
		}
	}

	private boolean isEntity(final EntityType<?> entityType) {
		return null != entityType.getJavaType().getAnnotation(Entity.class);
	}

	private boolean hasTableAnnotation(final EntityType<?> entityType) {
		return null != entityType.getJavaType().getAnnotation(Table.class);
	}

	@Transactional
	public void execute() {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
		entityManager.flush();

		for (final String tableName : tableNames) {
			entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
			entityManager.createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1").executeUpdate();
		}

		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
	}

	@Transactional
	public void executeInitializeTable() {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
		entityManager.flush();

		for (final String tableName : tableNames) {
			entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
		}

		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
	}
}
