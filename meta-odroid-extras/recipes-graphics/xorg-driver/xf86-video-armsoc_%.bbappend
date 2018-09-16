FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

MALI ?= ""

SRC_URI_append_odroid = " file://10-armsoc.conf"
SRC_URI_append_odroid = "\
	file://0001-Exynos-G2D-EXA-acceleration.patch \
	file://0002-Batch-copies.-Fix-line-endings.patch \
	file://0003-Support-16bit-RGB565-blits.patch \
	file://0004-Cleanup-G2D-on-exit.patch \
	file://0005-Solid-fill-acceleration.patch \
	file://0006-Don-t-batch-solid-fill-operations-to-avoid-overflowi.patch \
	file://0007-Catch-command-queue-overflow-on-solid-fill.-Support-.patch \
	file://0008-Enable-hardware-mouse-cursor.patch \
	file://0009-Disable-solid-fill-acceleration.patch \
	file://0010-Re-enable-solid-fill-acceleration.-Check-alu-operati.patch \
	file://0011-Add-options-to-disable-G2D-and-hardware-mouse-cursor.patch \
	file://0012-Fix-build.-Enable-full-screen-vsync.patch \
"

CONFFILES_${PN}_odroid = "${sysconfdir}/X11/xorg.conf.d/10-armsoc.conf"

do_install_append_odroid () {
        if test -s ${WORKDIR}/10-armsoc.conf; then
                install -d ${D}/${sysconfdir}/X11/xorg.conf.d
                install -m 0644 ${WORKDIR}/10-armsoc.conf ${D}/${sysconfdir}/X11/xorg.conf.d
        fi
}

FILES_{PN}_append_odroid = " ${sysconfdir}/X11/xorg.conf.d"

RDEPENDS_${PN} += " ${@bb.utils.contains('MACHINE_FEATURES', 'mali', '${MALI}', '', d)}"
