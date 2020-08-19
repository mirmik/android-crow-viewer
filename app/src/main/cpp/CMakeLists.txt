cmake_minimum_required(VERSION 3.4.1)

add_library(native-lib SHARED

        ../java/crow/Crow.cpp
        ../java/crow/CrowSpamSubscriber.cpp

        crow/crow/select.cpp
        crow/crow/src/address.cpp
        crow/crow/src/tower.cpp
        crow/crow/src/stdtime.cpp
        crow/crow/src/packet.cpp
        crow/crow/src/packet_ptr.cpp
        crow/crow/src/gateway.cpp
        crow/crow/src/hexer.c
        crow/crow/src/allocation_malloc.cpp
        crow/crow/src/print.cpp
        crow/crow/src/threads-posix.cpp
        crow/crow/proto/pubsub.cpp
        crow/crow/proto/node.cpp
        crow/crow/proto/node-sync.cpp
        crow/crow/proto/channel.cpp
        crow/crow/gates/udpgate.cpp

        igris/igris/dprint/dprint_func_impl.c
        igris/igris/dprint/dprint_stub.c
        igris/igris/util/numconvert.c
        igris/igris/util/hexascii.cpp
        igris/igris/util/string.cpp
        igris/igris/util/dstring.c
        igris/igris/util/stub.c
        igris/igris/util/crc.c
        igris/igris/util/emergency_stop.c
        igris/igris/util/printf_impl.c
        igris/igris/sync/syslock_mutex.cpp
        igris/igris/util/bug_abort.c
        igris/igris/osinter/wait-linux.cpp
        igris/igris/osinter/wait.c
        igris/igris/osutil/src/posix.cpp

        nos/nos/print/print.cpp
        nos/nos/fprint/fprint.cpp
        nos/nos/fprint/stdtype.cpp
        nos/nos/print/stdtype.cpp
        nos/nos/io/stdfile.cpp
        nos/nos/io/ostream.cpp
        nos/nos/io/impls/current_ostream_stdout.cpp

        native-lib.cpp )

include_directories(native-lib crow igris nos)

find_library(log-lib
             log )

target_link_libraries(
                       native-lib
                       ${log-lib} )