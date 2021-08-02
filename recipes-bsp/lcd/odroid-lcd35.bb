# Copyright (C) 2018 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Enable Odroid LCD 3.5 inch panel"
HOMEPAGE = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://odroid-lcd35.service \
           file://odroid-lcd35.sysvinit \
           file://lcd-blacklist.conf \
          "

inherit systemd update-rc.d

do_install:append () {
   install -D -m 0644 ${WORKDIR}/lcd-blacklist.conf ${D}${sysconfdir}/modprobe.d/lcd-blacklist.conf
   if [ "${@bb.utils.contains("DISTRO_FEATURES", "systemd", "yes", "no", d)}" = "yes" ]; then
       install -D -m 0644 ${WORKDIR}/odroid-lcd35.service ${D}${systemd_unitdir}/system/odroid-lcd35.service
   else
       install -D -m 0755 ${WORKDIR}/odroid-lcd35.sysvinit ${D}${sysconfdir}/init.d/odroid-lcd35
   fi
}

INITSCRIPT_NAME = "odroid-lcd35"
INITSCRIPT_PARAMS = "defaults"

SYSTEMD_SERVICE:${PN} = "odroid-lcd35.service"

FILES:${PN} += "${sysconfdir}/modprobe.d"

RDEPENDS:${PN}_odroid-c2 = "\
                 kernel-module-aml-i2c \
                 kernel-module-pwm-meson \
                 kernel-module-pwm-ctrl \
                 kernel-module-fbtft-device \
                 kernel-module-flexfb \
                 kernel-module-sx865x \
                "

COMPATIBLE_MACHINE = "(odroid-c2|odroid-xu4)"
