PACKAGECONFIG:remove:meson-gx  = "glamor"
XSERVER_RRECOMMENDS:remove:odroid = "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'xf86-input-libinput', '', d)}"
