package model;

public record ContactData(String FirstName, String MiddleName, String LastName, String Nickname, String Photo, String Title,
                          String Company, String Address, String TelephoneHome, String TelephoneMobile, String TelephoneWork, String TelephoneFax,
                          String Email, String Email2, String Email3, String Homepage, String Birthday, String Anniversary, String Group) {

    public ContactData() {
        this("", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "",
                "", "", "", "");
    }

    public ContactData withFirstName(String FirstName){
        return new ContactData(FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withMiddleName(String MiddleName){
        return new ContactData(this.FirstName, MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withLastName(String LastName){
        return new ContactData(this.FirstName, this.MiddleName, LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withAddress(String Address){
        return new ContactData(this.FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, Address, this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withTelephoneHome(String TelephoneHome){
        return new ContactData(this.FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withTelephoneMobile(String TelephoneMobile){
        return new ContactData(this.FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withTelephoneWork(String TelephoneWork){
        return new ContactData(this.FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, this.TelephoneMobile, TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withTelephoneFax(String TelephoneFax){
        return new ContactData(this.FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withEmail(String Email){
        return new ContactData(this.FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withEmail2(String Email2){
        return new ContactData(this.FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withEmail3(String Email3){
        return new ContactData(this.FirstName, this.MiddleName, this.LastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.TelephoneHome, this.TelephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }
}