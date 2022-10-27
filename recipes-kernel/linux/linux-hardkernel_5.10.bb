FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

KBRANCH = "odroid-5.10.y"

SRCREV = "c5b4b4f61d50fa1892bd0c34d03bd09670152497"
LINUX_VERSION ?= "5.10.27"

EXTRA_OEMAKE:append = " KBUILD=${B}"
require linux-hardkernel.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
SRC_URI = "git://github.com/tobetter/linux.git;branch=${KBRANCH};protocol=https \
"

SRC_URI:append = " file://defconfig"

KERNEL_EXTRA_FEATURES = ""

do_install:prepend() {
    bbnote "custom kernel_do_install customization"
    cp ${B}/arch/arm64/boot/dts/amlogic/*.dtb ${B}/arch/arm64/boot/dts/
}

COMPATIBLE_MACHINE = "(odroid-n2-hardkernel|odroid-c4-hardkernel|odroid-hc4-hardkernel)"
