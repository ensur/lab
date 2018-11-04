from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait


class WaitTools():
    def waitForCondition(driver, condition):
        try:
            WebDriverWait(driver, 1).until(condition)
            return True
        except Exception:
            return False

