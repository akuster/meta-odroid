#
# Copyright 2018 Armin Kuster <akuster808@gmail.com
#

DESCRIPTION = "Scripts to copy boot and rootFS to an Emmc device"
SECTION = "bootloaders"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

S = "${WORKDIR}"

do_patch[noexec] = "1"
do_compile[noexec] = "1"

SRC_URI = "file://emmc.tmp"
EMMC_DEVICE = "/dev/mmcblk0boot0"
EMMC_ROOTFS = "/dev/mmcblk0"
EMMC_MNT = "/mnt"
FSTYPE = "ext4"

CLEANBROKEN = "1"

do_configure () {
    cat ${B}/emmc.tmp > ${B}/emmc.sh
    echo "dd if=/emmc/bl1.bin.hardkernel of=${EMMC_DEVICE} conv=notrunc bs=512 seek=0" >> ${B}/emmc.sh
    echo "dd if=/emmc/bl2.bin.hardkernel of=${EMMC_DEVICE} conv=notrunc bs=512 seek=30" >> ${B}/emmc.sh
    echo "dd if=/emmc/u-boot-dtb.bin of=${EMMC_DEVICE} conv=notrunc bs=512 seek=62" >> ${B}/emmc.sh
    echo "dd if=/emmc/tzsw.bin.hardkernel of=${EMMC_DEVICE} conv=notrunc bs=512 seek=2110" >> ${B}/emmc.sh
    echo "sync" >> ${B}/emmc.sh

    echo "parted -s ${EMMC_ROOTFS} mklabel msdos" >> ${B}/emmc.sh
    echo "parted -s ${EMMC_ROOTFS} unit s mkpart primary fat32 8192 50789 " >> ${B}/emmc.sh
    echo "parted -s ${EMMC_ROOTFS} set 1 boot on" >> ${B}/emmc.sh
    echo "parted -s ${EMMC_ROOTFS} unit s mkpart primary ${FSTYPE} 57344 100%" >> ${B}/emmc.sh
    echo "parted ${EMMC_ROOTFS} print " >> ${B}/emmc.sh
    echo "sleep 3" >> ${B}/emmc.sh
    echo "umount  ${EMMC_MNT}" >> ${B}/emmc.sh
    echo "mkfs.${FSTYPE} -F ${EMMC_ROOTFS}p2" >> ${B}/emmc.sh
    echo "mkfs.msdos ${EMMC_ROOTFS}p1" >> ${B}/emmc.sh
    echo "mount -t ${FSTYPE}  ${EMMC_ROOTFS}p2 ${EMMC_MNT}" >> ${B}/emmc.sh
    echo "rsync -avP --exclude='/dev' --exclude='/proc' --exclude='/sys' --exclude='/emmc' --exclude='/mnt' --exclude='/run' / ${EMMC_MNT}" >> ${B}/emmc.sh
    echo "sleep 1" >> ${B}/emmc.sh
    echo "umount  ${EMMC_MNT}" >> ${B}/emmc.sh
    echo "sleep 2" >> ${B}/emmc.sh
    echo "mount ${EMMC_ROOTFS}p1 ${EMMC_MNT}" >> ${B}/emmc.sh
    echo "cp boot.scr ${EMMC_MNT}" >> ${B}/emmc.sh
    echo "umount  ${EMMC_MNT}" >> ${B}/emmc.sh
    echo "fi"  >> ${B}/emmc.sh

}

do_install () {
    install -d ${D}/emmc
    install ${B}/emmc.sh ${D}/emmc
}

FILES:${PN} = "/emmc"

RDEPENDS:${PN} = "coreutils rsync e2fsprogs parted dosfstools"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE  = "(odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-hc1)"

