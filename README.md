# Лабораторна робота: Java Data-Oriented Programming (DOP)

## Опис проєкту

Реалізація предметної моделі та сервісу обробки даних із використанням сучасних можливостей Java:
**Records**, **Sealed Types**, **Switch Expressions + Pattern Matching** та **Text Blocks**.

Дані (незмінні `record`-и) відокремлені від алгоритмів (клас `TransactionProcessor`).

**Тема:** Фінансова система транзакцій. Пакет: `ua.kpi.comsys.dop`.

| Тип | Опис |
|---|---|
| `Transaction` | `sealed interface`, `permits Deposit, Withdrawal, Transfer` |
| `Deposit(accountId, amount)` | поповнення рахунку |
| `Withdrawal(accountId, amount, category)` | зняття коштів |
| `Transfer(fromAccount, toAccount, amount)` | переказ між рахунками |
| `TransactionProcessor` | `process(Transaction)` повертає форматований Text Block |

Валідація в конструкторах record-ів: `amount != null` і `> 0`; текстові поля не `null` та не `isBlank()`
(інакше `IllegalArgumentException`). `process(null)` кидає
`IllegalArgumentException("Transaction cannot be null")`.

## Розрахунок варіанта

`V = N mod 3`, де N — номер залікової книжки (за відсутності — номер у списку групи).

N = 4, тому V = 4 mod 3 = **1** — «Фінансова система транзакцій».

## Вимоги

- JDK 21+
- Maven 3.8+

## Запуск тестів

```bash
mvn test
```

Звіт покриття (JaCoCo) після запуску: `target/site/jacoco/index.html`.

## Структура

```
src/main/java/ua/kpi/comsys/dop   # Transaction, Deposit, Withdrawal, Transfer, TransactionProcessor
src/test/java/ua/kpi/comsys/dop   # JUnit 5 тести
```
