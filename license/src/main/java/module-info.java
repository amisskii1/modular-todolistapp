module license {
    requires transitive spring.web;
    requires transitive com.fasterxml.jackson.databind;

    exports com.misskii.todolistapp.license.api;
    provides com.misskii.todolistapp.license.api.LicenseClientApi with com.misskii.todolistapp.license.LicenseClient;
}