package model;

public record ContactData(String id, String firstName, String middleName, String lastName,//String Nickname, String Photo, String Title, String Company, String Address,
                          String telephoneHome, String telephoneMobile//, String TelephoneWork, String TelephoneFax,
                          //String Email, String Email2, String Email3, String Homepage, String Birthday, String Anniversary, String Group
                          ) {

    public ContactData() {
        this("", "", "", "", "", ""//, "", "", "",
                //"", "", "", "", "", "", "",
                //"", "", "", ""
                );
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, //this.Nickname, this.Photo, this.Title, this.Company, this.Address,
                this.telephoneHome, this.telephoneMobile//, this.TelephoneWork, this.TelephoneFax,
                //this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withFirstName(String FirstName){
        return new ContactData(this.id, FirstName, this.middleName, this.lastName, //this.Nickname, this.Photo, this.Title, this.Company, this.Address,
                this.telephoneHome, this.telephoneMobile//, this.TelephoneWork, this.TelephoneFax,
                //this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group
                );
    }

    public ContactData withMiddleName(String MiddleName){
        return new ContactData(this.id, this.firstName, MiddleName, this.lastName, //this.Nickname, this.Photo, this.Title,
                //this.Company, this.Address,
                this.telephoneHome, this.telephoneMobile//, this.TelephoneWork,
                //this.TelephoneFax, this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withLastName(String LastName){
        return new ContactData(this.id, this.firstName, this.middleName, LastName, //this.Nickname, this.Photo, this.Title,
                //this.Company, this.Address,
                this.telephoneHome, this.telephoneMobile//, this.TelephoneWork,
                //this.TelephoneFax, this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

 /*   public ContactData withAddress(String Address){
        return new ContactData(this.firstName, this.middleName, this.lastName, this.Nickname, this.Photo, this.Title,
                this.Company, Address, this.telephoneHome, this.telephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }
  */

    public ContactData withTelephoneHome(String TelephoneHome){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, //this.Nickname, this.Photo, this.Title,
                //this.Company, this.Address,
                TelephoneHome, this.telephoneMobile//, this.TelephoneWork, this.TelephoneFax,
                //this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withTelephoneMobile(String TelephoneMobile){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, //this.Nickname, this.Photo, this.Title,
                //this.Company, this.Address,
                this.telephoneHome, TelephoneMobile//, this.TelephoneWork, this.TelephoneFax,
                //this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }


/*
    public ContactData withTelephoneWork(String TelephoneWork){
        return new ContactData(this.firstName, this.middleName, this.lastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.telephoneHome, this.telephoneMobile, TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withTelephoneFax(String TelephoneFax){
        return new ContactData(this.firstName, this.middleName, this.lastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.telephoneHome, this.telephoneMobile, this.TelephoneWork, TelephoneFax,
                this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withEmail(String Email){
        return new ContactData(this.firstName, this.middleName, this.lastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.telephoneHome, this.telephoneMobile, this.TelephoneWork, this.TelephoneFax,
                Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withEmail2(String Email2){
        return new ContactData(this.firstName, this.middleName, this.lastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.telephoneHome, this.telephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }

    public ContactData withEmail3(String Email3){
        return new ContactData(this.firstName, this.middleName, this.lastName, this.Nickname, this.Photo, this.Title,
                this.Company, this.Address, this.telephoneHome, this.telephoneMobile, this.TelephoneWork, this.TelephoneFax,
                this.Email, this.Email2, Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group);
    }
 */
}