PACKAGECONFIG_remove  = "${@bb.utils.contains('MACHINE_FEATURES', 'mali x11', 'gbm egl', '', d)}"
