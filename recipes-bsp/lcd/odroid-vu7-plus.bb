DESCRIPTION = "Enable Odroid LCD Vu7+  panel"
HOMEPAGE = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://odroid-vu7-plus.sysvinit"

inherit update-rc.d

do_install:append () {
       install -D -m 0755 ${WORKDIR}/odroid-vu7-plus.sysvinit ${D}${sysconfdir}/init.d/odroid-vu7-plus
}

INITSCRIPT_NAME = "odroid-vu7-plus"
INITSCRIPT_PARAMS = "defaults"

COMPATIBLE_MACHINE = "(odroid-xu3|odroid-xu3-lite|odroid-xu4|odroid-c2)"
