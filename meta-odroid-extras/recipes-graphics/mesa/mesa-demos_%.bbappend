PACKAGECONFIG:remove  = "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'gbm egl','', d)}"
