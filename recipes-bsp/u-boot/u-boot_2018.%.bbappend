FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"

inherit ${@oe.utils.conditional('MACHINE', 'odroid-xu4', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-xu3', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-c2', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-hc1', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-xu3-lite', 'uboot-boot-scr', '', d)}

DEPENDS += "u-boot-mkimage-native atf-native"
SRC_URI_append_odroid =  " file://0001-mmc-avoid-division-by-zero-in-meson_mmc_config_clock.patch"

SRC_URI_append_odroid-c2 = "\
    file://odroid-c2/aml_encrypt_gxb \
    file://odroid-c2/bl2.package  \
    file://odroid-c2/bl301.bin \
    file://odroid-c2/bl30.bin \
    file://odroid-c2/bl31.bin \
    file://0001-Add-default-bootargs.patch \
    "

do_compile_append_odroid-c2 () {

        fip_create --bl30 ${WORKDIR}/odroid-c2/bl30.bin --bl301 ${WORKDIR}/odroid-c2/bl301.bin --bl31 ${WORKDIR}/odroid-c2/bl31.bin --bl33 ${B}/${UBOOT_BINARY} ${B}/fip.bin
        fip_create --dump ${B}/fip.bin

        cat ${WORKDIR}/odroid-c2/bl2.package fip.bin > ${B}/boot_new.bin
        ${WORKDIR}/odroid-c2/aml_encrypt_gxb --bootsig --input ${B}/boot_new.bin --output ${B}/${UBOOT_BINARY}.tmp
        dd if=${B}/${UBOOT_BINARY}.tmp of=${B}/${UBOOT_BINARY} bs=512 skip=96
}

COMPATIBLE_MACHINE_odroid-c2  = "odroid-c2"
COMPATIBLE_MACHINE_odroid-xu3  = "odroid-xu3"
COMPATIBLE_MACHINE_odroid-xu4  = "odroid-xu4"
COMPATIBLE_MACHINE_odroid-xu3-lite  = "odroid-xu3-lite"
COMPATIBLE_MACHINE_odroid-hc1  = "odroid-hc1"
