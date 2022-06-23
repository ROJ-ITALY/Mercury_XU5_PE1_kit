#
# Yocto recipe to build a kernel module out of the kernel tree
# Stefano Gurrieri
#

DESCRIPTION = "uio vdw driver built as kernel module out of the kernel tree"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"


inherit module

SRC_URI = "git://github.com/ROJ-ITALY/uio-vdw-driver.git;protocol=https;branch=uio_kernel_space"

SRCREV = "${AUTOREV}"
PV = "1.0.0+gitr${SRCPV}"

S = "${WORKDIR}/git"
