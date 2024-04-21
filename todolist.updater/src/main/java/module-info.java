module todolist.updater {
    requires com.fasterxml.jackson.databind;

    exports com.misskii.todolistapp.updater.api;
    provides com.misskii.todolistapp.updater.api.UpdaterApi with com.misskii.todolistapp.updater.Updater;
}