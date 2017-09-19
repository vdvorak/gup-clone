package ua.com.itproekt.gup.server.api.rest.invitecodes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BonusRestControllerTest {

    private BankSession rest;

    @Before
    public void setUp() {
//        rest = new BankSession("http://gup.com.ua:8087");
    }

    @After
    public void tearDown() {
    }

    /**
     * * FUNCTION 'fn_check_internal_transactions(<current_user_id>, <by_type>)'
     * *****************************************************
     * SELECT * FROM fn_check_internal_transactions('1a2a3a4a5a6a7a8a9a0abccd', '');
     */
    @Test
    public void testGetAllPendingTransactions() {
        System.err.println("***Test user internal transaction for user with types***");

        String actual = rest.getAllPendingTransactions("571a2fdd681db5eee71086c0", "20");
        System.err.println( actual );
    }

    /**
     * FUNCTION 'fn_check_bonus_amount(<current_user_id>, <current_code>)'
     * .....................................................
     * SELECT * FROM fn_activate_invite_code('571a2fdd681db5eee71086c0', 'fejc9UNI1HdlRAd8BE9iRls9nEmGYMPlC5HWaYeld0TY5mCWnc') AS answer;
     */
    @Test
    public void testAddBonusByUserId() {
        System.err.println("***Test add bonus by UserId***");

//        String actual = rest.addBonusByUserId("578f34854c8e46b32797d92b", "XmltZ9X2wNLYMyvpqYIKRl5uE4ooWYHc3OWzS58iEhtO4Nx6rA");
//        String actual = rest.addBonusByUserId("1a2a3a4a5a6a7a8a9a0abccd", "P8W7m9p2HkyQkXve0hVEhc1FzXYmS6xSVUKX6cL6rj0JLlO2Bh");
        String actual = rest.addBonusByUserId("1a2a3a4a5a6a7a8a9a0abccd", "qBQ80MbbrmShAbEHuwexsHDUZjfQv7816GMrJBHBkbuPunex1v");
//        String actual = rest.addBonusByUserId("1a2a3a4a5a6a7a8a9a0abccd", "nCwlWHt1LWjoFUVx9FguVQrpIMwJh0o66tAZrqCvOjnNRha5DA");

        System.err.println( actual );
    }

    /**
     * FUNCTION 'fn_check_bonus_amount(<current_user_id>)'
     * .....................................................
     * SELECT * FROM fn_check_bonus_amount('1a2a3a4a5a6a7a8a9a0abccd') AS amount;
     */
    @Test
    public void testGetBonusByUserId() {
        System.err.println("***Test bonus by UserId***");

//        Integer actual = rest.getBonusByUserId("571a2fdd681db5eee71086c0"); // 578f34854c8e46b32797d92b // 571a2fdd681db5eee71086c0
//        System.err.println( actual );
    }

    /**
     * FUNCTION 'fn_buy_bonus_account(<current_user_id>, <current_trans_type>, <current_amount>, <current_offer_id>)'
     * .....................................................
     * SELECT * FROM fn_buy_bonus_account('571a2fdd681db5eee71086c0', 2003, 15, '57444d787688dcc2ed336db7') AS answer;
     */
    @Test
    public void testBuyByBonusAccount() {
        System.err.println("***Test buy bonus account***");

        String actual = rest.buyByBonusAccount("571a2fdd681db5eee71086c0", 2003, 15, "57444d787688dcc2ed336db7");
        System.err.println( actual );
    }
}
