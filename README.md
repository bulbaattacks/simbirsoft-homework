#### Тестируемый объект: https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager

#### Стек проекта:
- Java 17
- JUnit 5
- Maven
- Selenium
- Allure
- CI/CD: GitHub Actions

### 1. Создание клиента (Add Customer)
#### Шаги:
1. Открыть страницу https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager
2. Нажать кнопку "Add Custom" в верхней части окна.
3. Заполнить поля для регистрации клиента: "First Name", "Last Name", "Post Code":
   - значение поля "Post Code" генерируется функцией.
   - значение поля "First Name" генерируется функцией на основании значения поля "Post Code".
3. Нажать кнопку "Add Custom" в нижней части окна. 
4. В сообщении об успешной добавлении клиента нажать "ok". 
5. Нажать кнопку "Customers" в верхней части окна. 
6. Проверить, что клиент появился в разделе "Customers".

#### Ожидаемый результат:
1. После добавления клиента появилось сообщение "Customer added successfully with customer id" с идентификатором клиента.
2. Клиент успешно добавлен и отображается в разделе "Customers" с заполненными полями "First Name", "Last Name", "Post Code".

### 2. Сортировка клиентов по имени (First Name)
#### Шаги:
1. Открыть страницу https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager
2. Нажать кнопку "Customers" в верхней части окна.
3. Отсортировать клиентов по полю "First Name" в возрастающем порядке:
   - под строкой поиска 2 раза нажать на название колонки "First Name" в таблице с существующими клиентами.
4. Проверить значение поля "First Name" первого клиента в таблице. 

#### Ожидаемый результат:
1. Первым в таблице стоит клиент со значением "Albus" в поле "First Name".

### 3. Удаление клиента (Delete customer)
#### Шаги:
1. Открыть страницу https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager
2. Нажать кнопку "Customers" в верхней части окна.
3. Нажать кнопку "Delete" в строке таблицы с существующими клиентами:
   - клиент, которого необходимо удалить определяется функцией.
4. Проверить, что клиент удален.

#### Ожидаемый результат:
1. В таблице с существующими клиентами отсутствует удаленный клиент.

#### Запуск теста:
```
mvn clean verify
```
#### Построение отчета в Allure:
```
cd target
allure generate
allure open
```