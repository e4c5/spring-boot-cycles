# Spring Boot Cycles Testbed

A Spring Boot 2.5 project with **intentional circular dependencies** for testing cycle detection tools.

## Purpose

This project contains cycles that:
- **Compile successfully** (static analysis can run)
- **FAIL at runtime** (as intended for testing)

## Cycle Patterns

| Package | Type | Cycle | Hard/Soft |
|---------|------|-------|-----------|
| `simple/` | Field | Order ↔ Payment | Soft |
| `transitive/` | Field | Inventory → Supplier → Purchase → Inventory | Soft |
| `constructor/` | Constructor | User ↔ Notification | **Hard** |
| `setter/` | Setter | Report ↔ Data | Soft |
| `config/` | @Bean | CacheManager ↔ ConnectionPool | **Hard** |
| `complex/` | Field | Hub ↔ A, A ↔ B, Hub ↔ C | Soft |

## Hard vs Soft Cycles

| Cycle Type | Spring Boot 2.5 | 2.6+ | 2.6+ with `allow-circular-references=true` |
|------------|-----------------|------|-------------------------------------------|
| **Soft** (field/setter) | ✅ Works | ❌ Fails | ✅ Works |
| **Hard** (constructor/@Bean) | ❌ Always fails | ❌ Fails | ❌ Still fails |

**Key insight**: `spring.main.allow-circular-references=true` only helps with soft cycles. Hard cycles cannot be resolved by Spring regardless of settings.

## Building

```bash
mvn compile     # ✅ Succeeds
mvn test        # ✅ Passes (compile check only)
mvn spring-boot:run  # ❌ Fails (hard cycles)
```

## Usage with Cycle Detection Tool

```bash
java -jar antikythera-examples.jar cycle-detector \
  --base-path /home/raditha/csi/Antikythera/spring-boot-cycles/src/main/java \
  --dry-run
```
