module com.misskii.todolistapp.updater{
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;

    exports com.misskii.todolistapp.updater.api;
    provides com.misskii.todolistapp.updater.api.UpdaterApi with com.misskii.todolistapp.updater.Updater;
}