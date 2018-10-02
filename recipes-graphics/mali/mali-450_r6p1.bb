require mali.inc

DESCRIPTION = "Mali 450 GPU Binaries for ODROID-C2"

LIC_FILES_CHKSUM = "file://README.md;md5=b4d850abae9a934f53b165f0c71518b2"

DEPENDS += "patchelf-native"

TYPE = "Utgard"

SRCREV = "6ad971051be6336cf042c7fc0d31f3a92a5c3d9f"
SRC_URI = "git://github.com/mdrjr/c2_mali"

S = "${WORKDIR}/git"

do_install () {
	# Create MALI manifest
	install -m 755 -d ${D}${libdir} ${D}${libdir}/pkgconfig ${D}${includedir}
	install -m 0644 ${S}/pkgconfig/*.pc ${D}${libdir}/pkgconfig
	sed -i -e 's#^libdir=.*$#libdir=\$\{prefix\}${base_libdir}#g' ${D}${libdir}/pkgconfig/*.pc
	if [ "${USE_X11}" = "yes" ]; then
		cp -av --no-preserve=ownership ${S}/x11/mali_libs/lib*.so* ${D}${libdir}
		cp -av --no-preserve=ownership ${S}/x11/mali_headers/* ${D}${includedir}
	else
		cp -av --no-preserve=ownership ${S}/fbdev/mali_libs/lib*.so* ${D}${libdir}
		cp -av --no-preserve=ownership ${S}/fbdev/mali_headers/* ${D}${includedir}
		sed -i -e '/^Cflags:/s/$/ -DMESA_EGL_NO_X11_HEADERS/g' ${D}${libdir}/pkgconfig/egl.pc
	fi
	patchelf --set-soname libMali.so ${D}${libdir}/libMali.so
	ln -sf libMali.so ${D}/${libdir}/libOpenCL.so
}

RDEPENDS_${PN} = "kernel-module-mali-utgard"
RDEPENDS_${PN}_remove_odroid-c2-hardkernel = "kernel-module-mali-utgard"
COMPATIBLE_MACHINE = "odroid-c2"
