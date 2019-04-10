#Mali userland provides these
PROVIDES_remove  = "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'virtual/libgbm virtual/libwayland', '', d)}"

do_install_append_odroid () {
    if [ -n "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'mali', '', d)}" ]; then
        rm -f ${D}/${libdir}/libwayland-egl.so*
    fi
}
