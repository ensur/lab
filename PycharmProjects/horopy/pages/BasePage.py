class BasePage(object):
    def open(self, driver, url):
        driver.get(url)

    def __init__ (self, driver):
        self.driver = driver
        self.driver.implicitly_wait(5)
        self.timeout = 30

    def getPageHeader(self, driver):
        return driver.find_element_by_xpath("//h1").text