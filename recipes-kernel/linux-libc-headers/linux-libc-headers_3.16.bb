require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

do_install_armmultilib () {
        oe_multilib_header asm/auxvec.h asm/bitsperlong.h asm/byteorder.h asm/fcntl.h asm/hwcap.h asm/ioctls.h asm/kvm.h asm/mman.h asm/param.h
        oe_multilib_header asm/posix_types.h asm/ptrace.h  asm/setup.h  asm/sigcontext.h asm/siginfo.h asm/signal.h asm/stat.h  asm/statfs.h asm/swab.h  asm/types.h asm/unistd.h
}

SRC_URI[md5sum] = "5c569ed649a0c9711879f333e90c5386"
SRC_URI[sha256sum] = "4813ad7927a7d92e5339a873ab16201b242b2748934f12cb5df9ba2cfe1d77a0"
