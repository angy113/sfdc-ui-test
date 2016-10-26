package org.fundacionjala.sfdc.tests.account;

import org.fundacionjala.sfdc.pages.MainApp;
import org.fundacionjala.sfdc.pages.TabBar;
import org.fundacionjala.sfdc.pages.accounts.AccountDetail;
import org.fundacionjala.sfdc.pages.accounts.AccountForm;
import org.fundacionjala.sfdc.pages.accounts.AccountHome;
import org.fundacionjala.sfdc.framework.utils.JsonMapper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by Pablo Zubieta on 04/09/2015.
 */
public class CreateAccount {
    private AccountHome accountHome;
    private AccountDetail accountDetail;
    private AccountForm accountForm;
    private MainApp mainApp;
    private TabBar tabBar;
    public static final String ACCOUNT_DATA_PATH = "account/CreateAccountData.json";
    private Map<String, String> valuesMapJson;

    @BeforeMethod
    public void setUp() {
        valuesMapJson = JsonMapper.getMapJson(ACCOUNT_DATA_PATH);
        mainApp = new MainApp();
        tabBar = mainApp.goToTabBar();
        accountHome = tabBar.clickOnAccountsHome();
    }

    @Test
    public void createAccount(){
        accountForm = accountHome.clickNewButton();
        accountForm.fillTheForm(valuesMapJson);
        accountDetail = accountForm.clickSaveButton();
        valuesMapJson.keySet()
                .forEach(value -> Assert.assertEquals(accountDetail.getStrategyAssertMap().get(value).getText(),
                        valuesMapJson.get(value)));
    }

    @AfterMethod
    public void afterTest() {
        accountDetail.clickDeleteButton();
    }
}
