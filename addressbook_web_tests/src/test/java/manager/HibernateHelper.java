package manager;

import manager.hbm.GroupRecord;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private ApplicationManager manager;
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager){
        this.manager = manager;
        sessionFactory = new Configuration()
                        //.addAnnotatedClass(Book.class)
                        .addAnnotatedClass(GroupRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.group_id, record.name, record.group_header, record.group_footer);
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }
}