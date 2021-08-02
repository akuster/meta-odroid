# Copyright (C) 2018 Armin Kuster <akuster@gmailcom>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Provide EDID config files for LCD panels like VU7plus"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://1024x768.bin"
          
do_install() {
	install -d  ${D}${nonarch_base_libdir}/firmware/edid
	cp ${WORKDIR}/*.bin ${D}${nonarch_base_libdir}/firmware/edid
}

FILES:${PN} += "${nonarch_base_libdir}/firmware/edid/*"

COMPATIBLE_MACHINE = "(odroid-xu3|odroid-xu3-lite|odroid-xu4|odroid-c1|odroid-c2)"
