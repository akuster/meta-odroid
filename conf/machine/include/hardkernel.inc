# common Hardkernel settings
#

PREFERRED_PROVIDER_virtual/kernel = "linux-hardkernel"
PREFERRED_VERSION_linux-hardkernel ?= "4.9%"

#specify u-boot parameters
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-hardkernel"
PREFERRED_PROVIDER_u-boot = "u-boot-hardkernel"
PREFERRED_VERSION_u-boot-hardkernel = "2015.01"

SERIAL_CONSOLES = "115200;ttyS0"
UBOOT_CONSOLE = "console=ttyS0,115200"

UBOOT_BINARY = "u-boot.bin"

UBOOT_ENTRYPOINT = "0x01080000"
UBOOT_LOADADDRESS = "0x1080000"

UBOOT_SCRIPT = "boot.ini"
UBOOT_ENV_SUFFIX = "ini"
UBOOT_ENV_CONFIG = "${B}/${UBOOT_ENV}.${UBOOT_ENV_SUFFIX}"

KERNEL_IMAGETYPE = "Image.gz"

IMAGE_BOOT_FILES = "${UBOOT_SCRIPT} ${KERNEL_IMAGETYPE} ${@make_dtb_boot_files(d)} config.ini"

# Review these blacklists when using > 4.9 kernel since they might work and need for
# blacklisting not required

SKIP_RECIPE[kernel-selftest] = "kernel-selftest-1.0/tools/testing/selftests/bpf: No such file or directory."
SKIP_RECIPE[perf] = "needs python2 with kernel 4.9"
