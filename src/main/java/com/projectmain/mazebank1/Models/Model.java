package com.projectmain.mazebank1.Models;


import com.projectmain.mazebank1.Views.ViewFactory;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.time.LocalDate;


public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;

//    Client Data Section
    private Client client;
    private boolean clientLoginSuccessFlag;
//    Admin Data Section
    private boolean adminLoginSuccessFlag;
    private final ObservableList<Client> clients;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();

//        Client Data Section
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);

//        Admin Data Section
        this.adminLoginSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();
    }


    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
    public DatabaseDriver getDatabaseDriver(){
        return databaseDriver;
    }


    //    Client Method Section
    public boolean getClientLoginSuccessFlag(){
        return this.clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean flag){
        this.clientLoginSuccessFlag = flag;
    }

    public Client getClient() {
        return client;
    }

    public void evaluateClientCred(String pAddress, String password){
        CheckingAccount checkingAccount;
        SavingAccount savingAccount;
        ResultSet resultSet = databaseDriver.getClientData(pAddress,password);
        try {
            if(resultSet.isBeforeFirst()){
                this.client.firstnameProperty().set(resultSet.getString("FirstName"));
                this.client.lastnameProperty().set(resultSet.getString("LastName"));
                this.client.payeeAddressProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                this.client.dateCreatedProperty().set(date);
                checkingAccount = getCheckingAccount(pAddress);
                savingAccount = getSavingsAccount(pAddress);
                this.client.checkingAccountProperty().set(checkingAccount);
                this.client.savingsAccountProperty().set(savingAccount);
                this.clientLoginSuccessFlag = true;


            }
            }catch (Exception e) {
            e.printStackTrace();
        }
    }

//Admin Method Section

    public boolean getAdminLoginSuccessFlag(){
       return this.adminLoginSuccessFlag();
    }

    private boolean adminLoginSuccessFlag() {
        return this.adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag){
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }
    public void evaluationAdminCred(String username, String password){
        ResultSet resultSet = databaseDriver.getAdminData(username, password);
        try {
            if(resultSet.next()){
                this.adminLoginSuccessFlag = true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void setClients(){
        CheckingAccount checkingAccount;
        SavingAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getAllClientsDate();
        try {
            while (resultSet.next()){
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String payeeAddress = resultSet.getString("PayeeAddress");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                checkingAccount = getCheckingAccount(payeeAddress);
                savingsAccount = getSavingsAccount(payeeAddress);
                clients.add(new Client(fName, lName, payeeAddress, checkingAccount, savingsAccount, date));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


//UTILITY METHODS SECTIONS

    public CheckingAccount getCheckingAccount(String pAddress){
        CheckingAccount account = null;
        ResultSet resultSet = databaseDriver.getCheckingAccountData(pAddress);

        try {
            String num = resultSet.getString("AccountNumber");
            int tLimit = (int) resultSet.getDouble("TransactionLimit");
            double balance = resultSet.getDouble("Balance");
            account = new CheckingAccount(pAddress, num, balance, tLimit);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return account;

    }

    public SavingAccount getSavingsAccount(String pAddress){
        SavingAccount account = null;
        ResultSet resultSet = databaseDriver.getSavingAccountData(pAddress);

        try {
            String num = resultSet.getString("AccountNumber");
            int wLimit = (int) resultSet.getDouble("WithdrawalLimit");
            double balance = resultSet.getDouble("Balance");
            account = new SavingAccount(pAddress, num, balance, wLimit);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return account;

    }

    public ObservableList<Client> searchClients(String pAddress){
        ObservableList<Client> searchResult = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchClient(pAddress);
        try {
            CheckingAccount checkingAccount = getCheckingAccount(pAddress);
            SavingAccount savingAccount = getSavingsAccount(pAddress);
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dateParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            searchResult.add(new Client(fName,lName,pAddress,checkingAccount,savingAccount,date));


        }catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }

}
// the Model class is implemented as a singleton to ensure that there is only one instance of the class created,
// and to provide access to the ViewFactory object throughout the application