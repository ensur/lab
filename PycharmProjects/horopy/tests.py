import unittest
from selenium import webdriver

from pages.horoPage import HoroPage
from pages.algiz import Algiz


class Tests(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome("C:/driver/chromedriver.exe")

    def tearDown(self):
        self.driver.close()

    def testHoroPage(self):
        horo = HoroPage(self.driver)
        #Открываем страницу Шаг в будущее
        horo.open(self.driver, "https://horo.mail.ru/runes/divination/1/")
        #Проверяем, что изначально в поле стоит 1
        self.assertEqual(horo.listValIs(self.driver), "1")
        #Устанавливаем значение 3
        horo.setListVal(self.driver, 3)
        #Проверяем скролл списка
        self.assertTrue(horo.scrollList(self.driver))
        #Печатаем в поле Вопрос
        horo.printAsk(self.driver, "Вопрос")
        #Переход на новую страницу, pip не установил page(по неизвестным пока причинам), поэтому переход к новому экземпляру класса не использовал
        horo.clickButton(self.driver)

    def testAlgiz(self):
        algiz = Algiz(self.driver)
        #Открываем страницу
        algiz.open(self.driver, "https://horo.mail.ru/runes/tile/15/")
        #Проверяем, что страница нужная
        self.assertTrue(algiz.clickRuneAndGetTitle(self.driver, 1).__contains__(algiz.getPageHeader(self.driver)))
        algiz.open(self.driver, "https://horo.mail.ru/runes/tile/15/")
        #Проверяем работоспособность списка(перенёс не полную реализацию)
        self.assertTrue(algiz.checkRuneList(self.driver))


if __name__ == '__main__':
    unittest.main()
