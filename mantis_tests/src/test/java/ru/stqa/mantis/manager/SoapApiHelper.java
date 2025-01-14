package ru.stqa.mantis.manager;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import ru.stqa.mantis.model.IssueData;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class SoapApiHelper extends HelperBase {
    private final MantisConnectPortType mantis;

    public SoapApiHelper(ApplicationManager manager) {
        super(manager);
        try {
            mantis = new MantisConnectLocator().getMantisConnectPort(
                    new URL(manager.property("soap.endPoint")));
        } catch (ServiceException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

//    Ссылки
//    Документация (можно также открыть на локальной инсталляции Mantis):
//    https://www.mantisbt.org/bugs/api/soap/mantisconnect.php?wsdl
//
//    Библиотека, которая содержит готовый к использованию SOAP-клиент для Mantis:
//    https://central.sonatype.com/artifact/biz.futureware.mantis/mantis-axis-soap-client
//
//    Плагин для генерации кода из WSDL-описания веб-сервиса:
//    https://github.com/yupzip/wsdl2java


//    Настройка
//    Для работы с Mantis по протоколу SOAP нужно, чтобы в PHP была включена поддержка SOAP. Для того, чтобы включить её (или проверить, что она включена), откройте в текстовом редакторе файл C:\xampp\php\php.ini , найдите там строчку
//
//    ;extension=php_soap.dll
//            либо
//
//            ;extension=php_soap
//            либо
//            ;extension=soap
//            (конкретный вид этой строчки зависит от версии PHP) и раскомментируйте её, то есть удалите точку с запятой в начале (если это уже сделано, то ничего менять не нужно).
//
//    После внесения изменений перезапустите веб-сервер Apache из панели управления XAMPP.

    public void createIssue(IssueData issueData) {
        try {
            var categories = mantis.mc_project_get_categories(
                    manager.property("web.username"),
                    manager.property("web.password"),
                    BigInteger.valueOf(issueData.project()));
            var issue = new biz.futureware.mantis.rpc.soap.client.IssueData();
            issue.setSummary(issueData.summary());
            issue.setDescription(issueData.description());
            var projectId = new ObjectRef();
            projectId.setId(BigInteger.valueOf(issueData.project()));
            issue.setProject(projectId);
            issue.setCategory(categories[0]);
            mantis.mc_issue_add(
                    manager.property("web.username"),
                    manager.property("web.password"),
                    issue);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
