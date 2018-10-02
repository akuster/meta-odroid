require mali.inc

DEFAULT_PREFERENCE = "-1"

DESCRIPTION = "Mali 450 GPU Binaries for ODROID-C2"

LIC_FILES_CHKSUM = "file://${S}/../../../../README.md;md5=b4d850abae9a934f53b165f0c71518b2"

DEPENDS += "patchelf-native"

TYPE = "Utgard"

SRC_URI = "https://github.com/superna9999/meson_gx_mali_450/releases/download/for-4.15/buildroot_openlinux_kernel_4.9_20180131_mali.tar.gz"
SRC_URI += "file://README.md"

SRC_URI[md5sum] = "3b7df406c0061d44c5cfdbae5f4ebe07"
SRC_URI[sha256sum] = "8283ea5bb1f064bcc3811a95c8e3c818cf8dfdf7143c39b4059cb1f7062b9dab"

S = "${WORKDIR}/buildroot_openlinux/buildroot/package/meson-mali"

do_install () {
	# Create MALI manifest
	install -m 755 -d ${D}${libdir} ${D}${libdir}/pkgconfig ${D}${includedir}
	cp -av --no-preserve=ownership ${S}/lib/pkgconfig/* ${D}${libdir}/pkgconfig
	cp -av --no-preserve=ownership ${S}/include/{EGL,GLES*,KHR} ${D}${includedir}
	if [ "${USE_X11}" = "yes" ]; then
		cp -av --no-preserve=ownership ${S}/lib/arm64/r7p0/m450-X/*.so ${D}${libdir}
		cp -av --no-preserve=ownership ${S}/include/EGL_platform/platform_x11/* ${D}${includedir}/EGL
	fi
	if [ "${USE_WL}" = "yes" ]; then
		cp -av --no-preserve=ownership ${S}/lib/arm64/r7p0/m450/wayland/*.so ${D}${libdir}
		cp -av --no-preserve=ownership ${S}/include/EGL_platform/platform_wayland/* ${D}${includedir}/EGL
		cp -av --no-preserve=ownership ${S}/lib/libwayland*.so* ${D}${libdir}
	fi
	if [ "${USE_DFB}" = "yes" ]; then
		cp -av --no-preserve=ownership ${S}/lib/arm64/r7p0/m450/fbdev/*.so ${D}${libdir}
		cp -av --no-preserve=ownership ${S}/include/EGL_platform/platform_fbdev/* ${D}${includedir}/EGL
	fi
	cp -av --no-preserve=ownership ${S}/lib/libE*.so* ${D}${libdir}
	cp -av --no-preserve=ownership ${S}/lib/libG*.so* ${D}${libdir}
	cp -av --no-preserve=ownership ${S}/lib/libgbm.so* ${D}${libdir}
	patchelf --set-soname libMali.so ${D}${libdir}/libMali.so
	ln -sf libMali.so ${D}/${libdir}/libOpenCL.so
}

RDEPENDS_${PN} = "kernel-module-mali-utgard"
RDEPENDS_${PN}_remove_odroid-c2-hardkernel = "kernel-module-mali-utgard"
COMPATIBLE_MACHINE = "odroid-c2"
