package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import models.Day;
import models.GenericMedicine;
import models.MedicineUnit;
import utils.DBManager;
// import utils.SMSGatewayManager;

@WebListener
public class AppListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();

        System.out.println("                        ");
        System.out.println("######## HMS Starting ########");

        System.out.println("------- Configure Database --------");
        DBManager.setDbDriver(context.getInitParameter("dbdriver"));
        DBManager.setDbProtocol(context.getInitParameter("dbprotocol"));
        DBManager.setDbHost(context.getInitParameter("dbhost"));
        DBManager.setDbPort(context.getInitParameter("dbport"));
        DBManager.setDbName(context.getInitParameter("dbname"));
        DBManager.setDbUser(context.getInitParameter("dbuser"));
        DBManager.setDbPassword(context.getInitParameter("dbpassword"));
        DBManager.configureDataBase();
        
        // System.out.println("------- Configure Email --------");
        // EmailManager.setAppEmail(context.getInitParameter("app_email"));
        // EmailManager.setAppEmailsPassword(context.getInitParameter("app_emails_password"));
        // EmailManager.setSmtpHost(context.getInitParameter("smtp_host"));
        // EmailManager.setSmtpPort(context.getInitParameter("smtp_port"));
        // EmailManager.configureEmail();

        // System.out.println("------- Configure Captcha Keys --------");
        // // SiteKey is already set in the context-param...        
        // CaptchaManager.setSecretKey(context.getInitParameter("captcha_secret_key"));

        // System.out.println("------- Configure SMS Gateway Start --------");
        // SMSGatewayManager.setAccountSID(context.getInitParameter("twilio_account_sid"));
        // SMSGatewayManager.setAuthToken(context.getInitParameter("twilio_auth_token"));
        // SMSGatewayManager.setSenderPhone(context.getInitParameter("twilio_sender"));
        // SMSGatewayManager.configureTwilio();

        // System.out.println("------- Load Countries --------");
        // context.setAttribute("countries", Country.collectAllCountries());

        System.out.println("######## HMS Started ########");
        
        System.out.println("------Load Units------");
        context.setAttribute("units", MedicineUnit.getAllUnits());

        System.out.println("------Load Generic Medicines------");
        context.setAttribute("genericMedicine" , GenericMedicine.getAllGenericMedicines());

        System.out.println("------Load Days------");
        context.setAttribute("days" , Day.getDays());
        System.out.println(Day.getDays());
    }

    public void contextDestroyed(ServletContextEvent ev) {

    }
}