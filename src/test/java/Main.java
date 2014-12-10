import FunctionalTests.*;
import UsedByAll.CsvUsersReader;
import UsedByAll.RegionMatch;
import UsedByAll.TestUser;

public class Main
{
    public static void main(String[] args)
    {
        String scheme = "xmlm.t4web.com.ua/"; // Урл проверяемого сайта. В будущем надо вынести в конфиг-файлq
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
                try { if (newLoginFreeAPITest.runLoginFreeAPITests(scheme))
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
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_Registration("))
            {
                Registration newRegistration = new Registration(); // Создаём объект теста
                // Вызов метода, переходящего на главную страницу проекта
                try { newRegistration.setUp("http://" + scheme); }
                catch (Exception e) { e.printStackTrace(); return;}
                //Вызов метода, запускающего тест регистрации
                try { newRegistration.testReg(testUser[i]); }
                catch (Exception e) { e.printStackTrace(); return;}
                //Вызов метода окончания теста
                if(!RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_Registration(WithoutClose)_"))
                {
                    try { newRegistration.tearDown(); }
                    catch (Exception e) { e.printStackTrace(); return; }
                }
            }
            // Восстановление пароля
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_PasswordRecovery("))
            {
                PasswordRecovery newUserPasswordRecovery = new PasswordRecovery(); // Создаём объект теста
                // Вызов метода, переходящего на главную страницу проекта
                try { newUserPasswordRecovery.setUp("http://" + scheme); }
                catch (Exception e) { e.printStackTrace(); return;}
                //Вызов метода, запускающего тест восстановления пароля
                try { newUserPasswordRecovery.testUserPasswordRecovery("http://" + scheme, testUser[i]); }
                catch (Exception e) { e.printStackTrace(); return;}
                //Вызов метода окончания теста
                if(!RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_PasswordRecovery(WithoutClose)_"))
                {
                    try { newUserPasswordRecovery.tearDown(); }
                    catch (Exception e) { e.printStackTrace(); return;}
                }
            }
            // Авторизация
            if(RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_UserLogin("))
            {
                UserLogin newUserLogin = new UserLogin(); // Создаём объект теста
                // Вызов метода, переходящего на главную страницу проекта
                try { newUserLogin.setUp("http://" + scheme); }
                catch (Exception e) { e.printStackTrace(); return;}
                //Вызов метода, запускающего тест восстановления пароля
                try { newUserLogin.testUserLogin(testUser[i]); }
                catch (Exception e) { e.printStackTrace(); return;}
                if(!RegionMatch.IsStringRegionMatch(testUser[i].getUseInTest(), "_UserLogin(WithoutClose)_"))
                {
                    try { newUserLogin.tearDown(); }
                    catch (Exception e) { e.printStackTrace(); return;}
                }
            }
            System.out.println("Закончил тестировать с помощью пользователя № " + (i+1) + " - " + testUser[i].getFullName());
        }
        System.out.println("Тестирование успешно закончено");
    }
}