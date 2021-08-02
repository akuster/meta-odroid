require mali.inc

DESCRIPTION = "Mali G31 GPU Binaries for ODROID-C4"
LIC_FILES_CHKSUM = "file://END_USER_LICENCE_AGREEMENT.txt;md5=3918cc9836ad038c5a090a0280233eea"

TYPE = "mali-g31"

DEPENDS:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'virtual/mesa', '', d)}"

BRANCH = "mali-t62x_r12p0_00rel0"
SRCREV = "595cb959b48ccc1b9154fac5988191a1c3fffe3b"
SRC_URI = "git://github.com/akuster/arm-mali.git;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_install () {
        # Create MALI manifest
        install -m 755 -d ${D}/${libdir}
        if [ "${USE_X11}" = "yes" ]; then
		install ${S}/${TYPE}/x11/libmali.so ${D}/${libdir}
	fi
        if [ "${USE_WL}" = "yes" ]; then
		install ${S}/${TYPE}/wayland/libmali.so ${D}/${libdir}
        fi
        if [ "${USE_DFB}" = "yes" ]; then
		install ${S}/${TYPE}/fbdev/libmali.so ${D}/${libdir}
        fi

        ln -sf libmali.so ${D}/${libdir}/libEGL.so.1
        ln -sf libEGL.so.1 ${D}/${libdir}/libEGL.so
        ln -sf libmali.so ${D}/${libdir}/libGLESv1_CM.so.1
        ln -sf libGLESv1_CM.so.1 ${D}/${libdir}/libGLESv1_CM.so
        ln -sf libmali.so ${D}/${libdir}/libGLESv2.so.1
        ln -sf libGLESv2.so.1 ${D}/${libdir}/libGLESv2.so
	ln -sf libmali.so ${D}/${libdir}/libOpenCL.so.1
	ln -sf libOpenCL.so.1 ${D}/${libdir}/libOpenCL.so

	if [ "${USE_WL}" = "yes" ]; then
		ln -sf libmali.so ${D}/${libdir}/libgbm.so.1
		ln -sf libgbm.so.1 ${D}/${libdir}/libgbm.so
		ln -sf libmali.so ${D}/${libdir}/libwayland-egl.so.1
		ln -sf libwayland-egl.so.1 ${D}/${libdir}/libwayland-egl.so
	fi
}
FILES:${PN} = "${libdir}/lib*.so*"

RDEPENDS:${PN} += "kernel-module-mali-g31"
 
COMPATIBLE_MACHINE = "odroid-c4"

