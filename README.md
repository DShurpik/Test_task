Test API project.

For starting all tests with creating allure report use maven command:
mvn clean test -DsuiteXmlFile='test' allure:report

Because 22.02.2023y website http://3.68.165.45/swagger-ui.html#/player-controller didn't work, i didn't have 
opportunity to checked and built tests for "Update user".

Bugs which I managed to find:

1. Mixed requests. Create User must be "POST".
                    GetPlayerByPlayerId must be "GET".
2. When i created user with identical login, but with another information, for examle Age, happened rewrite 
information, how method "PATCH".
3. Use border values methodology, i found, user with 60 years registration was successfully, but if i used
16 years, registration was failed.


