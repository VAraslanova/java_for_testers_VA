package model;

public record ContactData(String id, String firstName, String middleName, String lastName, String Photo,//String Nickname,  String Title, String Company,
                          String address,
                          String home, String mobile, String work, //String TelephoneFax,
                          String email, String email2, String email3//, String Homepage, String Birthday, String Anniversary, String Group
                          ) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "",
                "", ""//
                // , "", "", "", "", "",
                //"", "", "", ""
                );
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.Photo, this.address,//this.Nickname,  this.Title, this.Company,
                this.home, this.mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, this.email3
        //, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withFirstName(String FirstName){
        return new ContactData(this.id, FirstName, this.middleName, this.lastName, this.Photo, this.address,//this.Nickname, this.Photo, this.Title, this.Company, this.Address,
                this.home, this.mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
                );
    }

    public ContactData withMiddleName(String MiddleName){
        return new ContactData(this.id, this.firstName, MiddleName, this.lastName, this.Photo, this.address,//this.Nickname, this.Photo, this.Title,
                //this.Company,
                this.home, this.mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withLastName(String LastName){
        return new ContactData(this.id, this.firstName, this.middleName, LastName, this.Photo, this.address,//this.Nickname, this.Photo, this.Title,
                //this.Company,
                this.home, this.mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withAddress(String Address){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.Photo, //this.Nickname,  this.Title,
                //this.Company,
                Address, this.home, this.mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
                );
    }

    public ContactData withHome(String Home){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.Photo, this.address,//this.Nickname, this.Photo, this.Title,
                //this.Company,
                Home, this.mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withMobile(String Mobile){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.Photo, this.address,//this.Nickname, this.Photo, this.Title,
                //this.Company,
                this.home, Mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withPhoto(String Photo){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, Photo, this.address,//this.Nickname, this.Photo, this.Title,
                //this.Company,
                this.home, this.mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }



    public ContactData withWork(String Work){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.Photo, this.address,//this.Nickname, this.Photo, this.Title,
                //this.Company,
                this.home, this.mobile, Work, //this.TelephoneFax,
                this.email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
                );
    }

//    public ContactData withFax(String TelephoneFax){
//        return new ContactData(this.firstName, this.middleName, this.lastName, this.Photo, //this.Nickname,  this.Title, this.Company,
//                this.address, this.home, this.mobile, this.work, TelephoneFax//,
//                //this.Email, this.Email2, this.Email3, this.Homepage, this.Birthday, this.Anniversary, this.Group
//                );
//    }

    public ContactData withEmail(String Email){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.Photo, //this.Nickname, this.Title, this.Company,
                this.address, this.home, this.mobile, this.work, //this.TelephoneFax,
                Email, this.email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
                );
    }

    public ContactData withEmail2(String Email2){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.Photo, //this.Nickname, this.Title, this.Company,
                this.address, this.home, this.mobile, this.work, //this.TelephoneFax,
                this.email, Email2, this.email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }

    public ContactData withEmail3(String Email3){
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.Photo, //this.Nickname, this.Title, this.Company,
                this.address, this.home, this.mobile, this.work, //this.TelephoneFax,
                this.email, this.email2, Email3
                //, this.Homepage, this.Birthday, this.Anniversary, this.Group
        );
    }
}