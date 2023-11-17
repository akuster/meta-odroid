# Remove gbm when building for eglfs ( mali fbdev driver will be used in this case )
NOGBM = "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'gbm', '', d)}"
PACKAGECONFIG_GRAPHICS:remove:odroid = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', '${NOGBM}', d)}"
