require mali.inc

DESCRIPTION = "Mali G31 GPU Binaries for ODROID-C4"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

DEPENDS:append = " patchelf-native ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'virtual/mesa', '', d)}"

SRCREV = "5963003d4d86d9820a606f7301b3b76ac718b8fb"
BRANCH = "aml64_buildroot_master_c4"
SRC_URI = "git://github.com/hardkernel/buildroot_linux_amlogic_meson_mali;branch=${BRANCH};protocol=https \
           file://0001-gles2-Update-gl2ext.h.patch \
          "
S = "${WORKDIR}/git"

do_install() {
        # Create MALI manifest
        install -m 755 -d ${D}${libdir} ${D}${libdir}/pkgconfig ${D}${includedir}
	      cp -av --no-preserve=ownership ${S}/include/EGL ${D}${includedir}
	      cp -av --no-preserve=ownership ${S}/include/GLES* ${D}${includedir}
	      cp -av --no-preserve=ownership ${S}/include/KHR ${D}${includedir}
        install -Dm 0644 ${S}/lib/pkgconfig/egl.pc ${D}${libdir}/pkgconfig/egl.pc
        install -Dm 0644 ${S}/lib/pkgconfig/glesv2.pc ${D}${libdir}/pkgconfig/glesv2.pc
	      if [ "${USE_WL}" = "yes" ]; then
		            install ${S}/lib/arm64/dvalin/${PV}/gbm/libMali.so ${D}/${libdir}
                install -Dm 0644 ${S}/include/EGL_platform/platform_wayland/eglplatform.h ${D}${includedir}/EGL/eglplatform.h
                install -Dm 0644 ${S}/include/EGL_platform/platform_wayland/wayland_window.h ${D}${includedir}/EGL/wayland_window.h
                install -Dm 0644 ${S}/include/EGL_platform/platform_wayland/gbm/gbm.h ${D}${includedir}/gbm.h
		            ln -sf libMali.so ${D}/${libdir}/libgbm.so.1
		            ln -sf libgbm.so.1 ${D}/${libdir}/libgbm.so
		            ln -sf libMali.so ${D}/${libdir}/libwayland-egl.so.1
		            ln -sf libwayland-egl.so.1 ${D}/${libdir}/libwayland-egl.so
                install -Dm 0644 ${S}/lib/pkgconfig/gbm/gbm.pc ${D}${libdir}/pkgconfig/gbm.pc
                install -Dm 0644 ${S}/lib/pkgconfig/wayland-egl.pc ${D}${libdir}/pkgconfig/wayland-egl.pc
                sed -ie "s#17.1.8#100.1.1#" ${D}${libdir}/pkgconfig/gbm.pc
        else
		            install ${S}/lib/arm64/dvalin/${PV}/fbdev/libMali.so ${D}/${libdir}
                install -Dm 0644 ${S}/include/EGL_platform/platform_fbdev/eglplatform.h ${D}${includedir}/EGL/eglplatform.h
                install -Dm 0644 ${S}/include/EGL_platform/platform_fbdev/fbdev_window.h ${D}${includedir}/EGL/fbdev_window.h
        fi
        sed -i -e '/^Cflags:/s/$/ -DMESA_EGL_NO_X11_HEADERS/g' ${D}${libdir}/pkgconfig/egl.pc
        sed -i -e "s#0.99#2.0.0#" ${D}${libdir}/pkgconfig/*.pc
        ln -sf libMali.so ${D}/${libdir}/libEGL.so.1
        ln -sf libEGL.so.1 ${D}/${libdir}/libEGL.so
        ln -sf libMali.so ${D}/${libdir}/libGLESv1_CM.so.1
        ln -sf libGLESv1_CM.so.1 ${D}/${libdir}/libGLESv1_CM.so
        ln -sf libMali.so ${D}/${libdir}/libGLESv2.so.1
        ln -sf libGLESv2.so.1 ${D}/${libdir}/libGLESv2.so
	      ln -sf libMali.so ${D}/${libdir}/libOpenCL.so.1
	      ln -sf libOpenCL.so.1 ${D}/${libdir}/libOpenCL.so
        patchelf --set-soname libMali.so ${D}${libdir}/libMali.so
}

FILES:${PN} = "${libdir}/lib*.so*"

RDEPENDS:${PN} += "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "wayland", "", d)}"

COMPATIBLE_MACHINE = "odroid-c4"
