require kernel-module-mali-midgard.inc

BRANCH = "TX011-SW-99002-r28p0-01rel0"
SRC_URI = "git://github.com/akuster/mali-kernel-modules.git;branch=${BRANCH};protocol=https \
           file://fix_kbase_gpuprosp_for_5.0_kernel.patch \
           file://0001-s-vm_insert_pfn-vmf_insert_pfn-g.patch"

SRCREV = "2512258bd03d9660f03168c04593b9feda47ba02"

python __anonymous() {
    platform = d.getVar('MALI_DRIVER_PLATFORM', True)
    if not platform:
        platform = "devicetree"

    config = ["CONFIG_MALI_MIDGARD=m",
              "CONFIG_MALI_GATOR_SUPPORT=y",
              "CONFIG_MALI_EXPERT=y",
              "CONFIG_MALI_PLATFORM_FAKE=y",
              "CONFIG_MALI_PLATFORM_THIRDPARTY=y",
              "CONFIG_MALI_PLATFORM_THIRDPARTY_NAME=" + platform]

    if platform == "5422":
        d.appendVar('SRC_URI', ' ' + d.getVar('5422_PATCH', True))

    for c in config:
        d.appendVar('MALI_FLAGS', '-D' + c + ' ')
        d.appendVar('MALI_KCONFIG', c + ' ')
}
export INSTALL_MOD_DIR = "kernel/mali"
