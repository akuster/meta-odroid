LICENSE = "Proprietary"
SECTION = "libs"

DEPENDS += "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "virtual/mesa", " ", d)}"
DEPENDS += "${@bb.utils.contains("DISTRO_FEATURES", "x11", " virtual/libx11 libxext libdrm libxfixes libxdamage", " ", d)}"

PROVIDES = "virtual/gpu"
PROVIDES += "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'virtual/egl virtual/libgles1 virtual/libgles2 virtual/libgbm', '', d)}"
PROVIDES += "${@bb.utils.contains("DISTRO_FEATURES", "wayland", " virtual/libwayland-egl", " ", d)}"

INSANE_SKIP:${PN} = "ldflags dev-so"
INSANE_SKIP:${PN}:append:libc-musl = " file-rdeps"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

USE_X11 = "${@bb.utils.contains("DISTRO_FEATURES", "x11", "yes", "no", d)}"
USE_DFB = "${@bb.utils.contains("DISTRO_FEATURES", "directfb", "yes", "no", d)}"
USE_WL = "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "yes", "no", d)}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

# Shared libs from mali package build aren't versioned, so we need
# to force the .so files into the runtime package (and keep them
# out of -dev package).
FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so"

RREPLACES:${PN} += "libegl libegl1 libgles1 libgles2 libglesv2-2"
RREPLACES:${PN} += "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "libgbm libwayland-egl.so", " ", d)}"
RPROVIDES:${PN} += "libegl libegl1 libgles1 libgles2 libglesv2-2"
RPROVIDES:${PN} += "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "libgbm libwayland-egl.so", " ", d)}"
RCONFLICTS:${PN} += "libegl libegl1 libgles1 libgles2 libglesv2-2"
RCONFLICTS:${PN} += "${@bb.utils.contains("DISTRO_FEATURES", "wayland", "libgbm libwayland-egl.so", " ", d)}"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN} ${PN}-dev"
