from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from tools import waitTools
import time

from pages.BasePage import BasePage
from pages.algiz import Algiz
class HoroPage(BasePage):
    button = "//div[contains(@class, 'button')][text()='Гадать']"
    form = "//div[text()='Конкретный вопрос или предмет гадания']//..//input[contains(@class, 'input__field')]"


    def isListOpen(self, driver):
        return waitTools.WaitTools.waitForCondition(driver, EC.element_to_be_clickable((By.XPATH, "//div[contains(@class, 'dropdown_scrollable js-select js-module dropdown_active')]")))

    def setListLocator(self, driver):
        if HoroPage.isListOpen(self, driver):
            return "//div[contains(@class, 'dropdown_scrollable js-select js-module{}')]".format(" dropdown_active")# конструкция типа locator.format(anotherLocator) некорректно обрабатывает строку в переменной, поэтому пришлось не использовать переменные
        else:
            return "//div[contains(@class, 'dropdown_scrollable js-select js-module{}')]".format("")

    def listValIs(self, driver):
        locator = HoroPage.setListLocator(self, driver)
        return driver.find_element_by_xpath(locator).text

    def setListVal(self, driver, number):
        locator = "//div[contains(@class, 'suggest__item')][text()='{}']".format(number)
        if HoroPage.isListOpen(self, driver):
            driver.find_element_by_xpath(locator).click()
        else:
            driver.find_element_by_xpath("//div[contains(@class, 'dropdown_scrollable js-select js-module')]").click()
            driver.find_element_by_xpath(locator).click()

    def scrollList(self, driver):
        driver.find_element_by_xpath("//div[contains(@class, 'dropdown_scrollable')]").click()
        scrollBar = driver.find_element_by_xpath("//div[contains(@class, 'js-scrollbar__track__drag')]//div")
        try:
            dragger = ActionChains(driver)
            numberOfPixelsToDragTheScrollbarDown = 10
            while not waitTools.WaitTools.waitForCondition(driver, EC.visibility_of_element_located((By.XPATH, "//div[contains(@class, 'suggest__item')][text()='7']"))):
                dragger.move_to_element(scrollBar).click_and_hold(scrollBar).move_by_offset(0, numberOfPixelsToDragTheScrollbarDown).release(scrollBar).perform()
            return True
        except Exception:
            return False

    def printAsk(self, driver, ask):
        driver.find_element_by_xpath(HoroPage.form).send_keys(ask)

    def clickButton(self, driver):
        driver.find_element_by_xpath(HoroPage.button).click



