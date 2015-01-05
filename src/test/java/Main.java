import ApiTests.BackendAPITest;
import ApiTests.LoginFreeAPITest;
import FunctionalTests.Testing.ChangeMailTest;
import FunctionalTests.Testing.RecoveryTest;
import FunctionalTests.Testing.RegistrationTest;
import UsedByAll.Config;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;

public class Main
{
    public static void main(String[] args)
    {
        String scheme = Config.getConfig().getScheme(); // Урл проверяемого сайта. В будущем надо вынести в конфиг-файлq
        // Проверка - не тестируем на продакшене
        if(RegionMatch.IsStringRegionMatch(scheme, "kairosplanet.com"))
        {
            System.out.println("Похоже что мы пытаемся запустить тестирование на продакшене. Нет.");
            return;
        }
        // Вызываем чтение массива пользователей из CSV-файла src/Users.csv. Адрес файла тоже можно вынести в конфиг-файл
        TestUser[] testUser = new CsvUsersReader().getUsersFromFile("src/Users.csv");



        // Проверяем, что массив пользователей не пуст
        if(testUser.length == 0){
            System.out.println("Нет пользователей для тестирования. Закрываюсь.");
            return;
        }
        System.out.println("Количество пользователей для тестирования: " + testUser.length);


        // Цикл, выполняющий тесты для пользователей, указанных в конфиге
        for (int i = 0; i < testUser.length; i++)
        {
            System.out.println("\n" + "Начинаю тестировать с помощью пользователя № " + (i+1) + " - " + testUser[i].getFullName() + ". Тест-кейсы, в которых он используется: " + testUser[i].getUseInTest());
            // FreeLogin API (authorization don't needed)
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_LoginFreeAPI("))
            {
                LoginFreeAPITest newLoginFreeAPITest = new LoginFreeAPITest(); // Создаём объект теста
                //Вызов метода, запускающего тесты LoginFree API
                try { if (newLoginFreeAPITest.runLoginFreeAPITests(scheme, testUser[i]))
                    System.out.println("Проверка LoginFree API пройдена");
                    else
                    System.out.println("Проверка LoginFree API НЕ пройдена");
                }
                catch (Exception e) { e.printStackTrace();
                    System.out.println("Проверка LoginFree API НЕ пройдена: " + e); }
                //Вызов метода окончания теста
            }
            // Backend API (needed authorization by admin)
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_BackendAPI("))
            {
                BackendAPITest newBackendAPITest = new BackendAPITest(); // Создаём объект теста
                //Вызов метода, запускающего тесты Backend API
                try { if (newBackendAPITest.runBackendAPITests(scheme, testUser[i]))
                    System.out.println("Проверка Backend API пройдена");
                    else
                    System.out.println("Проверка Backend API НЕ пройдена");
                }
                catch (Exception e) { e.printStackTrace();
                    System.out.println("Проверка Backend API НЕ пройдена: " + e); }
                //Вызов метода окончания теста
            }
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_RecoveryTest(")) {
                RecoveryTest newRecoveryTest = new RecoveryTest();

                try {
                    newRecoveryTest.recoveryTest();
                    System.out.println("RecoveryTest успешно пройден");

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("RecoveryTest НЕ пройден");
                }

            }
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_RegistrationTest(")) {
               RegistrationTest newRegistrationTest = new RegistrationTest();

                try {
                    newRegistrationTest.registrationTest();
                    System.out.println("Registration Test успешно пройден");

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Registration Test НЕ пройден");
                }

            }
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_ChangeMailTest(")) {
                ChangeMailTest changeMailTest = new ChangeMailTest();

                try {
                    changeMailTest.changeMailTest();
                    System.out.println("ChangeMail Test успешно пройден");

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ChangeMail Test НЕ пройден");
                }

            }

            System.out.println("Закончил тестировать с помощью пользователя № " + (i+1) + " - " + testUser[i].getFullName());
        }
        System.out.println("Тестирование успешно закончено");
    }
}