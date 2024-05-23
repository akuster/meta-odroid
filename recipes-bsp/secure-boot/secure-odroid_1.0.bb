DESCRIPTION = "Secure bootloader for Odroid devices supported by the hardkernel product"
SECTION = "bootloaders"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI:odroid-c2 = "file://odroid-c2/bl1.bin.hardkernel"

SRC_URI = "\
    file://bl1.bin.hardkernel \
    file://bl2.bin.hardkernel \
    file://tzsw.bin.hardkernel \
    "

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install:odroid-c2 () {
	install -d ${D}/boot
    	install -m 755  ${UNPACKDIR}/odroid-c2/bl1.bin.hardkernel ${D}/boot
}

do_install () {
    install -d ${D}/boot
    install -m 755  ${UNPACKDIR}/bl1.bin.hardkernel ${D}/boot
    install -m 755  ${UNPACKDIR}/bl2.bin.hardkernel ${D}/boot
    install -m 755  ${UNPACKDIR}/tzsw.bin.hardkernel ${D}/boot

    # if we want to support emmc booting
    if [ -n "${@bb.utils.contains('MACHINE_FEATURES', 'emmc', 'emmc', '', d)}" ]; then
        install -d ${D}/emmc
        install -m 755  ${UNPACKDIR}/bl1.bin.hardkernel ${D}/emmc
        install -m 755  ${UNPACKDIR}/bl2.bin.hardkernel ${D}/emmc
        install -m 755  ${UNPACKDIR}/tzsw.bin.hardkernel ${D}/emmc
    fi
}

inherit deploy

do_deploy:odroid-c2 () {
    install -d ${DEPLOYDIR}
    install -m 755  ${UNPACKDIR}/odroid-c2/bl1.bin.hardkernel ${DEPLOYDIR}
}

do_deploy () {
    install -d ${DEPLOYDIR}
    install -m 755  ${UNPACKDIR}/bl1.bin.hardkernel ${DEPLOYDIR}
    install -m 755  ${UNPACKDIR}/bl2.bin.hardkernel ${DEPLOYDIR}
    install -m 755  ${UNPACKDIR}/tzsw.bin.hardkernel ${DEPLOYDIR}
}

PACKAGES += "${@bb.utils.contains('MACHINE_FEATURES', 'emmc', '${PN}-emmc', '', d)}"

FILES:${PN} = "/boot"
FILES:${PN}-emmc = "/emmc"

addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE  = "(odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-c2|odroid-hc1)"
