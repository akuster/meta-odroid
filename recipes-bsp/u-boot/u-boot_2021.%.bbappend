FILESEXTRAPATHS:prepend := "${THISDIR}/u-boot:"

inherit ${@oe.utils.conditional('MACHINE', 'odroid-xu4', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-xu3', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-c2', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-hc1', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-xu3-lite', 'uboot-boot-scr', '', d)}
inherit ${@oe.utils.conditional('MACHINE', 'odroid-n2', 'uboot-boot-scr', '', d)}

DEPENDS += "u-boot-mkimage-native atf-native"
SRC_URI:append:odroid =  " file://0001-mmc-avoid-division-by-zero-in-meson_mmc_config_clock.patch"

SRC_URI:append:odroid-c2 = "\
    file://odroid-c2/aml_encrypt_gxb \
    file://odroid-c2/bl2.package  \
    file://odroid-c2/bl301.bin \
    file://odroid-c2/bl30.bin \
    file://odroid-c2/bl31.bin \
    file://0001-Add-default-bootargs.patch \
    "

SRC_URI:append:odroid-n2 = "\
    file://odroid-n2/aml_encrypt_g12b \
    file://odroid-n2/bl2.bin \
    file://odroid-n2/bl30.bin \
    file://odroid-n2/bl31.img \
    file://odroid-n2/ddr3_1d.fw \
    file://odroid-n2/ddr4_1d.fw \
    file://odroid-n2/ddr4_2d.fw \
    file://odroid-n2/lpddr4_1d.fw \
    file://odroid-n2/lpddr4_2d.fw \
    file://odroid-n2/diag_lpddr4.fw  \
    file://odroid-n2/piei.fw \
    file://odroid-n2/aml_ddr.fw \
    file://odroid-n2/blx_fix.sh \
    file://odroid-n2/bl301.bin \
    file://odroid-n2/acs.bin \
"

do_compile:append:odroid-c2 () {

        fip_create --bl30 ${WORKDIR}/odroid-c2/bl30.bin --bl301 ${WORKDIR}/odroid-c2/bl301.bin --bl31 ${WORKDIR}/odroid-c2/bl31.bin --bl33 ${B}/${UBOOT_BINARY} ${B}/fip.bin
        fip_create --dump ${B}/fip.bin

        cat ${WORKDIR}/odroid-c2/bl2.package fip.bin > ${B}/boot_new.bin
        ${WORKDIR}/odroid-c2/aml_encrypt_gxb --bootsig --input ${B}/boot_new.bin --output ${B}/${UBOOT_BINARY}.tmp
        dd if=${B}/${UBOOT_BINARY}.tmp of=${B}/${UBOOT_BINARY} bs=512 skip=96
}

do_compile:append:odroid-n2 () {
    cd ${WORKDIR}/odroid-n2
    chmod +x ./blx_fix.sh

    ./blx_fix.sh  bl30.bin zero_tmp bl30_zero.bin bl301.bin bl301_zero.bin ${B}/bl30_new.bin bl30

    ./blx_fix.sh bl2.bin zero_tmp bl2_zero.bin acs.bin bl21_zero.bin ${B}/bl2_new.bin bl2

    ./aml_encrypt_g12b --bl30sig --input ${B}/bl30_new.bin \
                    --output ${B}/bl30_new.bin.g12a.enc \
                    --level v3

    ./aml_encrypt_g12b --bl3sig --input ${B}/bl30_new.bin.g12a.enc \
                    --output ${B}/bl30_new.bin.enc \
                    --level v3 --type bl30

    ./aml_encrypt_g12b --bl3sig --input bl31.img \
                    --output ${B}/bl31.img.enc \
                    --level v3 --type bl31

    mv ${B}/u-boot.bin ${B}/bl33.bin
    ./aml_encrypt_g12b --bl3sig --input ${B}/bl33.bin --compress lz4 \
                    --output ${B}/bl33.bin.enc \
                    --level v3 --type bl33 --compress lz4

     ./aml_encrypt_g12b --bl2sig --input ${B}/bl2_new.bin \
                    --output ${B}/bl2.n.bin.sig

     ./aml_encrypt_g12b --bootmk \
        --output ${B}/u-boot.bin \
        --bl2 ${B}/bl2.n.bin.sig \
        --bl30 ${B}/bl30_new.bin.enc \
        --bl31 ${B}/bl31.img.enc \
        --bl33 ${B}/bl33.bin.enc \
        --ddrfw1 ddr4_1d.fw \
        --ddrfw2 ddr4_2d.fw \
        --ddrfw3 ddr3_1d.fw \
        --ddrfw4 piei.fw \
        --ddrfw5 lpddr4_1d.fw \
        --ddrfw6 lpddr4_2d.fw \
        --ddrfw7 diag_lpddr4.fw \
        --ddrfw8 aml_ddr.fw \
        --level v3
}

do_install:append () {
    if [ -n "${@bb.utils.contains('MACHINE_FEATURES', 'emmc', 'emmc', '', d)}" ]; then
         install -d ${D}/emmc
         install -m 644 ${B}/${UBOOT_BINARY} ${D}/emmc/${UBOOT_IMAGE}
         ln -sf ${UBOOT_IMAGE} ${D}/emmc/${UBOOT_BINARY}
         install -m 644 ${WORKDIR}/boot.scr ${D}/emmc
    fi
}

PACKAGES += "${@bb.utils.contains('MACHINE_FEATURES', 'emmc', '${PN}-emmc', '', d)}"

FILES:${PN}-emmc = "/emmc"

COMPATIBLE_MACHINE_odroid-c2  = "odroid-c2"
COMPATIBLE_MACHINE_odroid-xu3  = "odroid-xu3"
COMPATIBLE_MACHINE_odroid-xu4  = "odroid-xu4"
COMPATIBLE_MACHINE_odroid-xu3-lite  = "odroid-xu3-lite"
COMPATIBLE_MACHINE_odroid-hc1  = "odroid-hc1"
COMPATIBLE_MACHINE_odroid-n2  = "odroid-n2"
