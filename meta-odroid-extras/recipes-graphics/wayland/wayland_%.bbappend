#Mali userland provides these
PROVIDES:remove  = "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'virtual/libgbm virtual/libwayland', '', d)}"

do_install:append:odroid () {
    if [ -n "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'mali', '', d)}" ]; then
        rm -f ${D}/${libdir}/libwayland-egl.so*
    fi
}
