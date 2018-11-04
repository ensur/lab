from selenium.webdriver.common.by import By

from pages.BasePage import BasePage
from tools.waitTools import WaitTools
from selenium.webdriver.support import expected_conditions as EC


class Algiz(BasePage):
    listRuneDynamicAfter = "//span[text()='Все руны']/ancestor::div[contains(@class, 'wrapper_outside')]//div[@class='p-outside-block__content js-dynamic']//span[contains(@class, 'name')]"
    buttonMore = ("//div[text()='Ещё']/..")


    def clickRuneAndGetTitle(self, driver, runeNumber):
        driver.find_elements_by_xpath("//span[text()='Все руны']/ancestor::div[contains(@class, 'wrapper_outside')]//div[@class='p-outside-block__content']//span[contains(@class, 'name')]").__getitem__(runeNumber-1).click()
        return driver.title

    def checkRuneList(self, driver):
        quantityElBefore = len(driver.find_elements_by_xpath(Algiz.listRuneDynamicAfter))
        driver.find_element_by_xpath(Algiz.buttonMore).click()
        quantityElAfter = len(driver.find_elements_by_xpath(Algiz.listRuneDynamicAfter))
        return (quantityElBefore==0)and(quantityElAfter>quantityElBefore)





