#Mali userland provides these
do_install_append () {
    if [ -n "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'mali', '', d)}" ]; then
        rm -f ${D}/${libdir}/libwayland-egl.so*
    fi
}
