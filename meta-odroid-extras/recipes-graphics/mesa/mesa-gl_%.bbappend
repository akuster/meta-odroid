#Mali userland provides these
#
PROVIDES:remove:meson-gx  = "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'virtual/libgbm virtual/libgles1 virtual/libgles2 virtual/egl', '', d)}"
do_install:append:meson-gx () {
    if [ -n "${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'mali', '', d)}" ]; then
        rm -f ${D}/${libdir}/libEGL*
        rm -f ${D}/${libdir}/libGLESv1_CM.*
        rm -f ${D}/${libdir}/libGLESv2.*
        rm -f ${D}/${libdir}/libgbm*
        rm -f ${D}/${libdir}/libwayland-egl*
	rm -f ${D}${libdir}/pkgconfig/glesv1_cm.pc
        rm -f ${D}${includedir}/EGL/eglext.h
        rm -f ${D}${includedir}/EGL/eglmesaext.h
        rm -f ${D}${includedir}/EGL/egl.h
        rm -f ${D}${includedir}/EGL/eglplatform.h
        rm -f ${D}${includedir}/GLES2/gl2ext.h
        rm -f ${D}${includedir}/GLES2/gl2platform.h
        rm -f ${D}${includedir}/GLES2/gl2.h
        rm -f ${D}${includedir}/GLES/egl.h
        rm -f ${D}${includedir}/GLES/gl.h
        rm -f ${D}${includedir}/GLES/glext.h
        rm -f ${D}${includedir}/GLES/glplatform.h

    fi
}

PACKAGECONFIG:append:meson-gx = " ${@bb.utils.contains('MACHINE_FEATURES', 'lima', 'kmsro lima panfrost', '', d)}"
