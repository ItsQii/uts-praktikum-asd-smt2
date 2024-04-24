import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class BankAccount {
    private String accountNumber;
    private String name;
    private String phoneNumber;
    private String email;
    private double balance;

    public BankAccount(String accountNumber, String name, String phoneNumber, String email, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Saldo tidak mencukupi untuk penarikan");
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", balance='" + balance +
                '}';
    }
}

class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public void displaySortedAccounts() {
        accounts.stream()
                .sorted(Comparator.comparingDouble(BankAccount::getBalance).reversed())
                .forEach(System.out::println);
    }

    public void displayAccountsWithBalanceLessThan(double amount) {
        accounts.stream()
                .filter(a -> a.getBalance() < amount)
                .forEach(System.out::println);
    }

    public void findAccountByName(String name) {
        List<BankAccount> result = accounts.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            System.out.println("Nama akun tidak ditemukan:" + name);
        } else {
            result.forEach(System.out::println);
        }
    }

    public void depositToAccount(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Akun tidak ditemukan");
        }
    }

    public void withdrawFromAccount(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Akun tidak ditemukan");
        }
    }

    private BankAccount findAccount(String accountNumber) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        bank.addAccount(
                new BankAccount("160309273084", "Wallace", "1-458-264-3263", "ligula.Nullam@tacitisociosqu.edu", 10000));
        bank.addAccount(
                new BankAccount("161006170573", "Darius", "1-357-843-0547", "nec@lectusjusto.org", 7000));
        bank.addAccount(
                new BankAccount("162404012243", "Fuller", "571-7062", "convallis@Vestibulumanteipsum.org", 5000));
        bank.addAccount(
                new BankAccount("162705250112", "Malcolm", "623-0234", "porttitor.tellus.non@Curabitur.ca", 44000));
        bank.addAccount(
                new BankAccount("169712042416", "Geoffrey", "1-683-416-8323", "ut.pellentesque@luctusutpellentesque.com", 50000));
        bank.addAccount(
                new BankAccount("161007278862", "Rudyard", "650-5379", "proin.eget@velitegestaslacinia.ca", 1230000));
        bank.addAccount(
                new BankAccount("164603294259", "Troy", "897-7608", "pede.Suspendisse.dui@a.ca", 100000));
        bank.addAccount(
                new BankAccount("161807297229", "Alec", "792-4447", "on@mus.com", 34000));
        bank.addAccount(
                new BankAccount("169503136823", "Walter", "863-8209", "pellentesque.ut.ipsum@neque.ca", 334544));
        bank.addAccount(
                new BankAccount("169503136823", "Simon", "592-6919", "tellus.justo.sit@commodoauctor.net ", 2344));
        bank.addAccount(
                new BankAccount("168507083528", "Kamal", "1-115-330-7678", "dictum@nec.edu", 567770));
                System.out.println("Semua akun:");
                bank.displaySortedAccounts();
        
                System.out.println("\nAkun dengan saldo kurang dari 50000:");
                bank.displayAccountsWithBalanceLessThan(50000);
        
                System.out.print("\nCari Akun Berdasarkan Nama: ");
                String name = scanner.nextLine();
                bank.findAccountByName(name);
        
                System.out.println("\nMenyetor 2500 ke Akun 160309273084:");
                bank.depositToAccount("160309273084", 2500);
                System.out.println("Menarik 500 dari Akun 160309273084:");
                bank.withdrawFromAccount("160309273084", 500);
        
                scanner.close();

        scanner.close();
    }
}
