# add pstree
IMAGE_INSTALL_append = " psmisc"

# add tools to measure latencies in Linux
IMAGE_INSTALL_append = " rt-tests stress-ng"
