# add pstree
IMAGE_INSTALL_append = " psmisc"

# add tools to measure latencies in Linux
IMAGE_INSTALL_append = " rt-tests stress-ng"

# add uio vdw driver (kernel space)
IMAGE_INSTALL_append = " uio-vdw"

# add tools for managing Linux kernel modules
IMAGE_INSTALL_append = " kmod"
